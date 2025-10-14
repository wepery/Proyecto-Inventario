import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/core/services/login.service';

interface Usuario {
  id: number;
  nombre?: string;
  email?: string;
  // Agrega aquí otros campos que tenga tu usuario
}

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css'],
})
export class PerfilComponent implements OnInit {
  user: any | null = null;

  constructor(private readonly loginService: LoginService) {}

  ngOnInit(): void {
    this.user = this.loginService.getUser();
  }
}
