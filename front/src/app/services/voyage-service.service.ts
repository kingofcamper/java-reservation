import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VoyageServiceService {
  private apiUrl = 'http://localhost:8080/api/v1/voyages';

  constructor(private http: HttpClient) { }

  getAllvoyages(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getvoyageById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  createvoyage(voyage: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, voyage);
  }

  updatevoyage(id: number, voyage: any): Observable<any> {
    return this.http.put<any>(this.apiUrl+"/"+id, voyage);
  }

  deletevoyage(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
