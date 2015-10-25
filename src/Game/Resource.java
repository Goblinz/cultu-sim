package Game;

public class Resource {
  private String type;
  private int quantity;
  
  public Resource(String Type, int Quantity){
    //TODO check type
    type = Type;  
    quantity = Quantity;
  }
  public String getType(){return type;};
  public int getQuantity(){ return quantity; }
  public void setQuantity(int q){ quantity = q; }
  public void addQuantity(int q){ quantity =quantity+ q; }

}
