import {Component, NgModule, OnInit} from '@angular/core';
@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss']
})
export class InfoComponent implements OnInit {

  filter = [ 'weiblich' , 'männlich','20-30', '30-40', 'keine Vorerkrankungen'];
  selected_filter = ['weiblich'];

  constructor() { }

  ngOnInit(): void {
  }

  addToFilter(item) {
    if (!this.selected_filter.includes(item)) {
      this.selected_filter.push(item)
    }

  }

  removeFromFilter(item) {
    this.selected_filter = this.selected_filter.filter(inside => {
        return inside !== item
      }
    )
  }
}
