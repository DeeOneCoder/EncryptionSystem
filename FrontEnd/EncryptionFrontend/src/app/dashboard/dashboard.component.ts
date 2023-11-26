import { Component, OnInit, AfterViewInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router'
import { ActivityService } from '../activity.service';
import { UserActivity } from '../user-activity';
import * as $ from 'jquery';
import 'datatables.net';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userActivities!: UserActivity[];

  id!: number;

  constructor(private route: ActivatedRoute, private activityService: ActivityService, private router: Router) {

  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.activityService.getUsersActivities(this.id).subscribe(data => {
      this.userActivities = data;

      $(() => {
        $("#activityTable").DataTable({
          paging: true,
        });
      });
    });



    // Initialize DataTables after the view has been initialized

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
