Feature: US02 Usuario registrado inicia sesión con nombre de usuario y contraseña

Como usuario registrado quiero iniciar sesión con mi nombre de usuario y contraseña para usar la aplicación.

Scenario: Cuenta registrada

Given que el usuario quiere iniciar sesión e ingresa los datos correctos
When pulse iniciar sesión
Then el sistema le da acceso

Examples:

|User|Page|Button|Page|
|Any|Sing in|Iniciar sesión|User Profile|

 
#Scenario: Cuenta no registrada

#Given que el usuario quiere iniciar sesión e ingresa los datos incorrectos
#When pulse iniciar sesión
#Then el sistema le mostrará un mensaje diciendo que la cuenta no existe y la posibilidad de registrarse

#Examples:

#|User|Page|Button|Notification|
#|Any|Sing in|Iniciar sesión|Usuario inexistente - go to sing up|
 
#Scenario: Contraseña incorrecta

#Given que el usuario quiere iniciar sesión e ingresa un correo electrónico válido pero la contraseña es incorrecta
#When pulse iniciar sesión
#Then el sistema le mostrará un mensaje diciendo que la contraseña es incorrecta y mostrará la opción de “Olvidé mi contraseña”.

#Examples:

#|User|Page|Button|Notification|
#|Any|Sing in|Iniciar sesión|Contraseña incorrecta - try: olvide mi contraseña|


#Scenario: Campos faltantes

#Given que el usuario quiere iniciar sesión y dejó en blanco uno de los campos
#When pulse iniciar sesión
#Then el sistema le mostrará un mensaje diciendo que faltan datos por completar

#Examples:

#|User|Page|Button|Notification|
#|Any|Sing in|Iniciar sesión|Debe llenar todos los cambios|
