import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private apiUrl = 'http://localhost:8080/api/v1/users';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  getUserById(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  createUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, user);
  }

  updateUser(id: number, user: any): Observable<any> {
    return this.http.put<any>(this.apiUrl+"/"+id, user);
  }

  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}

