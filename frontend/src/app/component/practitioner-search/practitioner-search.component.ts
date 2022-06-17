import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {PractitionerService} from "../../service/practitioner.service";
import {Practitioner} from "../../dto/practitioner";
import {EnumLocation} from "../../datatype/enum-location";
import {EnumPractitioner} from "../../datatype/enum-practitioner";
import {EnumTime} from "../../datatype/enum-time";

@Component({
  selector: 'app-practitioner-search',
  templateUrl: './practitioner-search.component.html',
  styleUrls: ['./practitioner-search.component.scss']
})
export class PractitionerSearchComponent implements OnInit {

  searchForm = new FormGroup({
    location: new FormControl(),
    practitioner: new FormControl(),
    time: new FormControl()
  });

  searchedPract: Practitioner[]

  locationFromDb = Object.values(EnumLocation)
  practitionerFromDb = Object.values(EnumPractitioner)
  timeFromDb = Object.values(EnumTime)

  constructor(private formBuilder: FormBuilder, private practService: PractitionerService) {
    this.searchForm = this.formBuilder.group({
      location: this.formBuilder.array([]),
      practitioner: this.formBuilder.array([]),
      time: this.formBuilder.array([]),
    });
  }

  ngOnInit(): void {
    let url = new URL(window.location.href.toString())
    if (url.searchParams.get("speciality") != null) {
      this.searchForm.controls.practitioner.value.push(url.searchParams.get("speciality"))
    }
    if (url.searchParams.get("location") != null) {
      this.searchForm.controls.location.value.push(url.searchParams.get("location"))
    }
    if (url.searchParams.get("time") != null) {
      this.searchForm.controls.practitioner.value.push(url.searchParams.get("time"))
    }
    this.Search()
  }

  onCbChange(e, part) {
    const isArray: FormArray = this.searchForm.get(part) as FormArray;

    if (e.target.checked) {
      isArray.push(new FormControl(e.target.value));
    } else {
      let i: number = 0;
      isArray.controls.forEach((item: FormControl) => {
        if (item.value == e.target.value) {
          isArray.removeAt(i);
          return;
        }
        i++;
      });
    }
  }

  Search() {
    this.practService.search(this.searchForm.controls.practitioner.value,
      this.searchForm.controls.location.value,
      this.searchForm.controls.time.value).subscribe(
      {
        next: data => {
          this.searchedPract = data;
          console.log(data);
        },
        error: error => {
          console.error('Error fetching practitioners', error.message);
        }
      }
    )
  }

  removeItem(item, part) {
    let helper = part.value.filter(inside => {
        return inside !== item
      }
    )
    let length = part.value.length;
    for (let j = 0; j < length; j++) {
      part.value.pop()
    }
    for (let j = 0; j < helper.length; j++) {
      part.value.push(helper[j])
    }
  }
}
