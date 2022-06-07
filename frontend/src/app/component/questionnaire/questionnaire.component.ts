import {Component, Injectable, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-questionnaire',
  templateUrl: './questionnaire.component.html',
  styleUrls: ['./questionnaire.component.scss']
})
@Injectable({
  providedIn: 'root'
})
export class QuestionnaireComponent implements OnInit {

  genderInput = [
    {label: 'Männlich', value:'g_male'},
    {label: 'Weiblich', value:'g_female'},
    {label: 'Divers', value:'g_div'}
  ]

  body_elements;
  test = new Array();

  questionnaireForm = new FormGroup({
      gender: new FormControl(),
      age: new FormControl(),
      location: new FormControl(),
      pain: new FormControl(),
      pain_date: new FormControl(),
    })
  constructor(private formBuilder: FormBuilder) {
    this.questionnaireForm = this.formBuilder.group({
      gender:[''],
      age:[''],
      location:[''],
      pain:[''],
      pain_date:[''],
      // bodypart: [new Array()]
      bodypart: this.formBuilder.array([])

    });

  }

  svg_elements;
  ngOnInit(): void {
    this.svg_elements = Array.from(document.getElementsByTagName('svg'));
    // console.log(this.svg_elements)
    let children;
    this.svg_elements.forEach(function (el) {
      children = Array.from(el.childNodes);
      children.forEach( child => {
        if (child.tagName === 'path'){
          // console.log(child.id)
          let newbutton = document.createElement('div')
          newbutton.innerText = child.id
          newbutton.id = child.id + "-btn"
          newbutton.classList.add('btn-prim')
          newbutton.classList.add('btn')
          // console.log(el.id +"-btn")
          document.getElementById(el.id + "-btn").appendChild(newbutton)

        }
      })
    })
    this.body_elements = document.getElementsByTagName('path');
    // this.body_elements.forEach(function (el){
    //   el.addEventListener("click", () => {
    //     if(this.questionnaireForm.controls.bodypart.value.includes(el.id)) {
    //       let helper = this.questionnaireForm.controls.bodypart.value.filter(val => {
    //         return val !== el.id
    //       })
    //       let length =  this.questionnaireForm.controls.bodypart.value.length;
    //       for (let j = 0; j < length; j++) {
    //         this.questionnaireForm.controls.bodypart.value.pop()
    //       }
    //       for (let j = 0; j < helper.length; j++) {
    //         this.questionnaireForm.controls.bodypart.value.push(helper[j])
    //       }
    //
    //     } else {
    //       this.questionnaireForm.controls.bodypart.value.push(el.id);
    //     }
    //
    //       // console.log(this.questionnaireForm.controls.bodypart.value)
    //     })
    // })
    for (let i = 0; i < this.body_elements.length; i++) {
      this.body_elements[i].addEventListener("click", () => {
        if(this.questionnaireForm.controls.bodypart.value.includes(this.body_elements[i].id)) {
          let helper = this.questionnaireForm.controls.bodypart.value.filter(val => {
            return val !== this.body_elements[i].id
          })
          let length =  this.questionnaireForm.controls.bodypart.value.length;
          for (let j = 0; j < length; j++) {
            this.questionnaireForm.controls.bodypart.value.pop()
          }
          for (let j = 0; j < helper.length; j++) {
            this.questionnaireForm.controls.bodypart.value.push(helper[j])
          }

        } else {
          this.questionnaireForm.controls.bodypart.value.push(this.body_elements[i].id);
        }

        // console.log(this.questionnaireForm.controls.bodypart.value)
      })
    }
  }

  link = '/fragebogen';

  onTabClick(e) {
    let links = document.getElementsByClassName('tab-link');
    let input = document.getElementsByClassName('tab-pane');
    for (let i = 0; i < links.length; i++) {
      links[i].classList.remove('active');
      links[i].classList.remove('show');
      input[i].classList.remove('active');
      input[i].classList.remove('show');
    }
    document.getElementById(e + '-tab').classList.add('show');
    document.getElementById(e + '-tab').classList.add('active');
    document.getElementById(e).classList.add('show');
    document.getElementById(e).classList.add('active');

  }

  Questionnaire() {
    console.log(this.questionnaireForm.controls.gender.value)
    console.log(this.questionnaireForm.controls.age.value)
    console.log(this.questionnaireForm.controls.location.value)
  }

  previousTab() {
    let active = document.getElementsByClassName("show active")
    let elements = document.getElementsByClassName('tab-link');
    if (active[0].id !== elements[0].id){
      let previous = active[0].previousElementSibling.id;
      this.onTabClick(previous.substring(0, previous.length-4))
    }
  }

  nextTab() {
    let active = document.getElementsByClassName("show active")
    let elements = document.getElementsByClassName('tab-link');
    if (active[0].id !== elements[elements.length-1].id){
      let next = active[0].nextElementSibling.id;
      this.onTabClick(next.substring(0, next.length-4))
    }
  }

  showBodyPart(id: string) {
    if (document.getElementById(id + "-div").classList.contains('d-none')){
      document.getElementById(id + "-div").classList.remove('d-none')
    } else {
      document.getElementById(id + "-div").classList.add('d-none')
    }
  }
}
