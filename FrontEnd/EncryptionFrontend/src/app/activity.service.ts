import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserActivity } from './user-activity';
import { ActivatedRoute } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class ActivityService implements OnInit {

  private baseURL = "http://localhost:8080/encryption"
  private token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkYXZpZHNvbi5ha2luYWRlQGdtYWlsLmNvbSIsImlhdCI6MTcwMTAyNzAzOSwiZXhwIjoxNzAxMTEzNDM5fQ._E-kp_66ELpBrndO9IPKwzwqUFQzQizkmmASNNB2X2c";



  id!: number;

  constructor(private httpClient: HttpClient, private route: ActivatedRoute) { }
  ngOnInit(): void {
    this.id = Number(this.route.snapshot.params['id']);
  }

  private headers = new HttpHeaders({
    'Authorization': 'Bearer ' + this.token,
    'Content-Type': 'application/json'
  });

  getUsersActivities(id: number): Observable<UserActivity[]> {
    return this.httpClient.get<UserActivity[]>(`${this.baseURL}/activity/${id}`, { headers: this.headers });
  }

  findActivitiesByTitle(id: number, title: string): Observable<UserActivity> {
    return this.httpClient.get<UserActivity>(`${this.baseURL}/activity-title/${id}?title=${title}`, { headers: this.headers });
  }

  findActivitiesByDate(id: number, date: Date): Observable<any> {
    // Convert date to string or adjust as needed based on your backend implementation
    return this.httpClient.get(`${this.baseURL}/activity-date/${id}?date=${date.toISOString()}`, { headers: this.headers });
  }

  findActivitiesByType(id: number, type: string): Observable<any> {
    // Convert date to string or adjust as needed based on your backend implementation
    return this.httpClient.get(`${this.baseURL}/activity-date/${id}?date=${type}`, { headers: this.headers });
  }

  getAllEncryptionActivities(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/encrypted/${id}`);
  }

  getAllDecryptionActivities(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseURL}/decrypted/${id}`);
  }

  postEncryptionActivity(id: number, userActivity: any): Observable<any> {
    return this.httpClient.post(`${this.baseURL}/encrypt/${id}`, userActivity);
  }

  postDecryptionActivity(id: number, userActivity: any): Observable<any> {
    return this.httpClient.post(`${this.baseURL}/encrypt/${id}`, userActivity);
  }

}
