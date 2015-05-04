fxQuery [![Gitter chat](https://badges.gitter.im/Lintad.png)](https://gitter.im/Lintad) [![Build Status](https://travis-ci.org/Lintad/fxQuery.svg?branch=development)](https://travis-ci.org/Lintad/fxQuery)[(https://codeship.com/projects/YOUR_PROJECT_UUID/status?branch=development)]
=======


jQuery inspired JavaFX library for UI-Controller development


The project is an effort for easier JavaFX UI-Controller development 
that uses the idea of jQuery by calling node Ids and manipulate fxml 
elements same as DOM elements.

### How to use:
- Get the root pane of the fxml page(in this instance an AnchorPane)
- Add an id on the node you want to use
- Put a functionality by using the existing API built under the fxQuery


### Example Code:

```java
  /* Sample JavaFX Controller File */
  @FXML AnchorPane anchorpane /* An AnchorPane with with fx:id anchorPane */
  
  public void initialize(URL url, ResourceBundle rb){
    
      FXQuery fxQuery = new FXQuery(anchorPane);
      
      /* A Button with id sample-button-id  */
      fxQuery.$("#sample-button-id").click(new EventHandler<MouseEvent>(){
      
          @Override
          public void handle(MouseEvent event){
              /* Do something wild here */
          }

      });  

  }
```

Any node which is inside the given container(in this case an AnchorPane) 
can be automatically used by getting the id and put it in the selector 
function, which is the ```$("#element-id")``` method;

