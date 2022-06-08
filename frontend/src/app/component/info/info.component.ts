import {Component, NgModule, OnInit} from '@angular/core';
import {EnumPractitioner} from "../../datatype/enum-practitioner";
@Component({
  selector: 'app-info',
  templateUrl: './info.component.html',
  styleUrls: ['./info.component.scss']
})
export class InfoComponent implements OnInit {

  filter = [ 'weiblich' , 'männlich','20-30 Jahre', '30-40 Jahre', 'keine Vorerkrankungen'];
  selected_filter = ['weiblich'];


  information = [{
    tags: ['weiblich', 'männlich'],
    title: 'Internistische Vorsorgeuntersuchung',
    subtitle: 'Termin 1x im Jahr empfohlen',
    text: 'Bei der Internistischen Vorsorgeuntersuchung werden die klassischen Risikofaktoren und deren Folgeerkankungen untersucht. Routinemäßig gehören bei Risikopatienten das EKG, ein Belastungs-EKG, der Ultraschall von Herz, Bauch und Halsschlagader sowie eine ausführliche Laboruntersuchung zu diesem Programm.'
  },
  {
    tags: ['weiblich', 'männlich', 'keine Vorerkrankungen', EnumPractitioner.ZAHNARZT],
    title: 'Zahnärztliche Kontrolle',
    subtitle: 'Termin 2x im Jahr empfohlen',
    text: 'Bei den Kontrollen werden digitale Röntgenbilder angefertigt. Zähne und Zahnfleisch sowie die Mundhöhle werden kontrolliert. Die Zahnärztin oder der Zahnarzt informiert Sie, welche zahnärztlichen Maßnahmen nötig sind. Viele Behandlungen können auf Kassenkosten durchgeführt werden. Sind darüber hinaus Versorgungen wie etwa Kronen notwendig, erstellen wir für Sie einen Behandlungsplan mit den entstehenden Kosten. '
  },
  {
    tags: ['weiblich', '20-30 Jahre', '30-40 Jahre', EnumPractitioner.GYN],
    title: 'Gynäkologen ',
    subtitle: 'Termin 1x im Jahr empfohlen',
    text: ' Besonders wichtig sind der PAP- und HPV-Abstrich, denn durch die konsequente Kontrolle konnte in allen westlichen Industriestaaten die Sterblichkeit an Gebärmutterhalskrebs dramatisch reduziert werden. '
  },
    //todo add more information
  {
    tags: [],
    title: '',
    subtitle: '',
    text: ''
  }]

  constructor() { }

  ngOnInit(): void {
    this.filter = this.filter.concat(Object.values(EnumPractitioner))
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

  checkFilter(array) {
    for (let i = 0; i < array.length; i++) {
      if (this.selected_filter.includes(array[i])){
        return true;
      }
    }

  }
}
