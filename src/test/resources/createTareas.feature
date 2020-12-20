Feature:  When Do


  Scenario Outline: crear tarea

    Given la aplicacion when do esta abierta
    When click en el boton [AgregarTarea]
    And Ingresar a la caja de texto titulo de la tarea : <titulo>
    And Ingresar a la caja de texto descripcion de la tarea : <descripcion>
    And Clic en [GuardarTarea]
    Then la tarea  <titulo> debe estar en pantalla
    Examples:
      |   titulo     |                descripcion                                   |
      |   Tarea 01   |  Lorem ipsum dolor sit amet, consectetur adipiscing elit.    |
      |   Tarea 02   |  Lorem ipsum dolor sit amet, consectetur adipiscing elit.    |
      |   Tarea 03   |  Lorem ipsum dolor sit amet, consectetur adipiscing elit.    |
      |   Tarea 04   |  Lorem ipsum dolor sit amet, consectetur adipiscing elit.    |
      |   Tarea 05   |  Lorem ipsum dolor sit amet, consectetur adipiscing elit.    |