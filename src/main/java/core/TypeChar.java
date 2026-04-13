package core;

public record TypeChar (char typed, char expected) {

public TypedStatus getStatus() {
       if (this.typed==(' ')){
           return(TypedStatus.UNREACHED);

       } else if (this.typed==(expected)) {
           return(TypedStatus.CORRECT);
       }
       else {
           return(TypedStatus.INCORRECT);
       }
    }
}