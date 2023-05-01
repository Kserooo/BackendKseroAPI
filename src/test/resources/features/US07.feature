Feature: US07 Usuario mayorista inscribe un producto en su catálogo de la aplicación

Como usuario registrado, me gustaría inscribir un producto para poder empezar a recibir pedidos

Scenario: Usuario inscribe correctamente su producto

Given que el usuario mayorista desea inscribir un producto
When pulse la opción de inscribir producto
Then aparecerá un formulario el cual deberá completar con la información del producto
When termine y pulse el botón de finalizar
Then se le mostrará un mensaje diciendo que el producto se agrego correctamente a su catálogo
 
Examples:

|User|Button|Section|Button|Notification|
|Wholesaler|Inscribir producto|Formulario de producto|Finalizar|Agregado correctamente|

Scenario: Usuario no completa todo el formulario

Given que el usuario mayorista desea inscribir un producto
When pulse la opción de inscribir producto
Then aparecerá un formulario el cual deberá completar con la información del producto, pero no lo completa en su totalidad
When termine y pulse el botón de finalizar
Then se le mostrará un mensaje diciendo que falta información por completar

Examples:

|User|Button|Section|Button|Notification|
|Wholesaler|Inscribir producto|Formulario de producto|Finalizar|Todos los campos deben estar llenos|