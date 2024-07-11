import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ReservationServiceService {

  private apiUrl = 'http://localhost:8080/api/v1/reservations';

  constructor(private http: HttpClient) { }

  getAllreservations(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl+'/');
  }

  getreservationById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  createreservation(reservation: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, reservation);
  }

  updatereservation(id: number, reservation: any): Observable<any> {
    return this.http.put<any>(this.apiUrl+"/"+id, reservation);
  }

  deletereservation(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
