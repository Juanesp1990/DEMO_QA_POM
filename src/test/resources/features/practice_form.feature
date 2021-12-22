Feature: Formulario de estudiantes
  como empleado

  @regresion
  Scenario: ingreso de un estudiante con los campos obligatorios
    Given que el empleado administrativo se encuentra en la página web de los ingresos de estudiantes
    When el empleado administrativo ingresa los campos obligatios y confirma la acción
    Then el sistema deberá mostrar por la pantalla de registro del estudiante ingresado