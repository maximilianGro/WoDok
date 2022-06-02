import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";

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

  locationFromDb = ['1070 Wien', '1080 Wien', '1090 Wien']
  practitionerFromDb = ['Zahnarzt', 'Frauenarzt', 'Orthopäde']
  timeFromDb = ['8-9', '9-10', '10-11']

  constructor(private formBuilder: FormBuilder) {
    this.searchForm = this.formBuilder.group({
      location: this.formBuilder.array([]),
      practitioner: this.formBuilder.array([]),
      time: this.formBuilder.array([]),
    });
  }

  ngOnInit(): void {
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

  }

  removeItem(item, part) {
    let helper = part.value.filter(inside => {
        return inside !== item
      }
    )
    let length =  part.value.length;
    for (let j = 0; j < length; j++) {
      part.value.pop()
    }
    for (let j = 0; j < helper.length; j++) {
      part.value.push(helper[j])
    }
  }
}
