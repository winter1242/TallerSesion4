Feature:  When Do


  Scenario Outline: crear tarea

    Given la aplicacion when do esta abierta
    When click en el boton [AgregarTarea]
    And Ingresar a la caja de texto titulo de la tarea : <titulo>
    And Ingresar a la caja de texto descripcion de la tarea : <descripcion>
    And Clic en [GuardarTarea]
    And Visualizar las tareas creadas
    Then la tarea  <titulo> debe estar en pantalla
    Examples:
      | titulo   | descripcion |
      |   jb01   |  pw01    |
      |   jb02   |  pw02    |
      |   jb03   |  pw03    |
      |   jb04   |  pw04    |
      |   jb05   |  pw05    |