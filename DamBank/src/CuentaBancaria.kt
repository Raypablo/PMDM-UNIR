
class CuentaBancaria(val iban: String, val titular: String) { //el constructor de la clase es el (val iban: String, val titular: String)
    //si ponemos val solo podremos hacer get y si son var podremos hacer get y set

    private var saldo: Double = 0.0 // Se puede poner sin el : Double pero lo vamos a poner por ahora para tenerlo claro
    private var idMovimiento: Int = 1
    private val movimientos: MutableList<Movimiento> = mutableListOf() //es una lista propia de kotlin que en java sería igual que LinkedList (bastante recomendada)

    init {

        if(!validarIban(iban)){

            //Excepcion arbitraria
            throw IllegalArgumentException("El IBAN no es valido")

        }

    }

    fun obtenerDatosCuentaBancaria(): String{

        //return "Datos de la cuenta: IBAN" + this.iban + ", Titular: " + this.titular + ", Saldo: " + this.saldo
        return "Datos de la cuenta: IBAN ${this.iban} Titular: ${this.titular} Saldo: ${this.saldo}" //Otra forma de hace lo mismo que arriba

    }

    //validar titular
    private fun validarTitular(titular:String): Boolean {

        if(titular.length >= 3){

            return true

        }else

            return false

    }

    //validar formato IBAN
    private fun validarIban(iban:String): Boolean {

        return iban.matches(Regex("[A-Z]{2}[0-9]{22}"))

    }

    fun obtenerSaldo(): Double {

        return this.saldo

    }

    fun ingresarSaldo(cantidad: Double) {

        if(cantidad > 0.0){

            this.saldo += cantidad

            //Crear movimiento
            registrarCantidad(cantidad,"Ingreso")

        }else{

            println("La cantidad debe ser positiva")
        }

    }

    fun retirarSaldo(cantidad: Double) {

        if(cantidad > 0.0){

            if((this.saldo - cantidad) >= -50){

                this.saldo -= cantidad

                //Crear movimiento
                registrarCantidad(cantidad,"Retirada")

            }else{

                println("El saldo no puede ser meor a -50")

            }

        }else{

            println("La cantidad debe ser positiva")

        }

    }

    fun mostrarMovimientos(){

        if(movimientos.isEmpty()){

            println("No hay movimientos registrados")

        }else{

            this.movimientos.forEach{ println(it) } //it significa cada elemento
            //lo que hacemos con esto es el tipico for que recorre la coleccion e imprime por pantalla pero reducido a una linea

        }

    }
    private fun registrarCantidad(cantidad: Double, tipo: String) {

        val mo = Movimiento(this.idMovimiento, "Retirada", cantidad)
        this.movimientos.add(mo)
        this.idMovimiento++

    }

    //Complemento validador generico
    private fun validadorPatron(texto: String, patron:String): Boolean {

        return texto.matches(Regex(patron))

    }

}