
export class OposicionPlana{
    constructor(public idOpocion:string, public descripnion:string,public plan:Array<string>,public test:Array<string>){}
}

export class Temario{
    constructor(public numero:string,public tema:string, public apartados:Array<string>){}
}

export class Bloque{
    constructor(public bloque:string, public apartados:Array<Temario>){}
}