import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-decrypt',
  templateUrl: './decrypt.component.html',
  styleUrls: ['./decrypt.component.css']
})
export class DecryptComponent implements OnInit {
  id!: number;

  constructor(private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.id = this.activatedRoute.snapshot.params['id'];

  }

  showEncrypt(id: number) {
    return this.router.navigate(['/encrypt', { id }]);
  }

  encrypt() {
    return this.showEncrypt(this.id);
  }

  showDecrypt(id: number) {
    return this.router.navigate(['/decrypt', { id }]);
  }

  decrypt() {
    return this.showDecrypt(this.id);
  }

  showDashboard(id: number) {
    return this.router.navigate(['/dashboard', { id }]);
  }

  dashboard() {
    return this.showDashboard(this.id);
  }

  showFAQ() {
    return this.router.navigate(['/faq']);
  }

  faq() {
    return this.showFAQ();
  }

  showHowTo() {
    return this.router.navigate(['/howto']);
  }

  howto() {
    return this.showHowTo();
  }

}
