#include <Arduino.h>

int buttonPushCounter = 0;   // counter for the number of button presses
int buttonState = 0;         // current state of the button
int lastButtonState = 0;

int buttonPushCounter1 = 0;   // counter for the number of button presses
int buttonState1 = 0;         // current state of the button
int lastButtonState1 = 0;

int buttonPushCounter2 = 0;   // counter for the number of button presses
int buttonState2 = 0;         // current state of the button
int lastButtonState2 = 0;


void setup() {
  pinMode(4,INPUT);
  pinMode(5,OUTPUT);
  pinMode(7,OUTPUT);
  Serial.begin(9600);
  while(!Serial){
    ;
  }
}

void loop() {
    if(Serial.available()>0){
      byte input = Serial.read();
      if(input !=-1){
        digitalWrite(4,input);
        //Serial.println(input);
      }
    }
    int buttonState = digitalRead(7);  
//    Serial.write(buttonState);
//  delay(100);
//    if (digitalRead(5)==1){
//      Serial.write("1");
//    }
//    if (digitalRead(7)==0){
//      Serial.write("2");
//    }

buttonState = digitalRead(7);
buttonState1 = digitalRead(5);
buttonState2 = digitalRead(6);

  // compare the buttonState to its previous state
  if (buttonState != lastButtonState) {
    // if the state has changed, increment the counter
    if (buttonState == LOW) {
      // if the current state is HIGH then the button went from off to on:
      buttonPushCounter++;
      Serial.write(1);
    } else {
      // if the current state is LOW then the button went from on to off:
      //Serial.println("off");
    }
    // Delay a little bit to avoid bouncing
    delay(50);
  }
  if (buttonState1 != lastButtonState1) {
    // if the state has changed, increment the counter
    if (buttonState1 == LOW) {
      // if the current state is HIGH then the button went from off to on:
      buttonPushCounter1++;
      Serial.write(2);
    } else {
      // if the current state is LOW then the button went from on to off:
      //Serial.println("off");
    }
    // Delay a little bit to avoid bouncing
    delay(50);
  }
  if (buttonState2 != lastButtonState2) {
    // if the state has changed, increment the counter
    if (buttonState2 == LOW) {
      // if the current state is HIGH then the button went from off to on:
      buttonPushCounter2++;
      Serial.write(3);
    } else {
      // if the current state is LOW then the button went from on to off:
      //Serial.println("off");
    }
    // Delay a little bit to avoid bouncing
    delay(50);
  }
  // save the current state as the last state, for next time through the loop
  lastButtonState = buttonState;
  lastButtonState1 = buttonState1;
  lastButtonState2 = buttonState2;


}
