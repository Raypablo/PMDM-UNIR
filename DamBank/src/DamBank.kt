fun main(){

    println("Bienvenido a DamBank")

    println("Introduzca el IBAN de la cuenta bancaria (formato: 2 Letras y 22 digitos")
    val iban = readLine().orEmpty()

    println("Introduzca el titular de la cuenta")
    val titular = readLine().orEmpty()

    try {

        val cuentaBancaria = CuentaBancaria(iban, titular)
        println("Cuenta creada satisfactoriamente")

        var opcion: Int

        do{

            println("""
                Elige una opción:
                1. Ver datos de la cuenta
                2. Ver saldo
                3. Realizar un ingreso
                4. Realizar una retirada
                5. Ver movimintos
                6. Salir
            """.trimIndent())

            opcion = readLine()?.toIntOrNull() ?: 0

            when(opcion){

                1 -> cuentaBancaria.obtenerDatosCuentaBancaria()
                2 -> cuentaBancaria.obtenerSaldo()
                3 -> {

                    println("Introduzca la cantidad a ingresar")
                    val cantidad = readLine()?.toDoubleOrNull() ?: 0.0
                    cuentaBancaria.ingresarSaldo(cantidad)

                }
                4 -> {

                    println("Introduzca la cantidad a retirar")
                    val cantidad = readLine()?.toDoubleOrNull() ?: 0.0
                    cuentaBancaria.retirarSaldo(cantidad)

                }
                5 -> cuentaBancaria.mostrarMovimientos()
                6 -> println("Gracias por usar DamBank. Hasta luego!")
                else -> println("Opcion no valida")
            }

        }while(opcion != 6)

    }catch (e:IllegalArgumentException){

        println(e.message)

    }

}