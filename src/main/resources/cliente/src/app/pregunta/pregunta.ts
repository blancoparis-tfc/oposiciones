export class Opcion{
    public estado:string='';
    constructor(public apartado:string,public solucion:string,public correcta:boolean){}
}
export class Pregunta{
  //  public solucion:string='';
    constructor(public bloque:string,public numero:number,public enunciado:string,public anulada:boolean,public opciones:Array<Opcion>){}
}
