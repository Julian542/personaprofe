import { Component, OnInit } from '@angular/core';
import { PersonaServicioService } from 'src/app/servicios/persona-servicio.service';
import { Persona } from 'src/app/modelo/persona';
import { Router } from '@angular/router';

@Component({
  selector: 'app-tabla',
  templateUrl: './tabla.component.html',
  styles: []
})
export class TablaComponent implements OnInit {

  constructor(private servicio:PersonaServicioService, private router:Router) {
    
   }

   personas: Persona[] = [];
  ngOnInit() {
    this.getAll();
  }

  getAll(){
    this.servicio.getAll().subscribe((data)=>{
      this.personas = data;
    });
  }

  delete(id:number){
    this.servicio.delete(id).subscribe(
      () => {
      alert("¡Registro eliminado con exito!");
      window.location.reload();
    },
    ()=>{alert('El registro no pudo ser eliminado')});
  }

  agregar(){
    this.router.navigate(['persona/nuevo']);
  }

  actualizar(id:number){
    this.router.navigate(["persona/"+id]);
  }
}
