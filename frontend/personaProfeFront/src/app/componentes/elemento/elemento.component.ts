import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/modelo/persona';
import { Router, ActivatedRoute} from '@angular/router';
import { PersonaServicioService } from 'src/app/servicios/persona-servicio.service';

@Component({
  selector: 'app-elemento',
  templateUrl: './elemento.component.html',
  styleUrls: ['./elemento.component.css']
})
export class ElementoComponent implements OnInit {

  persona: Persona ={
    id:0,
    nombre:'',
    apellido:'',
    dni:0
  };

  constructor(private service: PersonaServicioService, private router:Router, private actRoute:ActivatedRoute) {

    this.actRoute.params.subscribe((data)=>{
      if(data['id'] != "nuevo"){
        this.getOne(data['id']);
      }else{
        this.update(data['id']);
      }
    });
   }

  ngOnInit() {
  }

  save(){
    this.actRoute.params.subscribe((data) => {
      if(data['id'] == "nuevo"){
        this.add();
      }else{
        this.update(data['id']);
      }
    });
  }

  add(){
    this.service.post(this.persona).subscribe( (data) => {
      this.router.navigate(['/']);
    });
  }

  update(id:number){
    this.service.put(id,this.persona).subscribe( (data) => {
      this.router.navigate(['/']);
    });
  }

  getOne(id:number){
    this.service.getOne(id).subscribe( (data) => {
      this.persona = data;
    });
  }

}
