package list;

public class LockDList extends DList {
	  protected LockDlinkNode newNode(Object item, DListNode prev, DListNode next) {
		    return new LockDlinkNode(item, prev, next);
		  }
	  public void lockNode(DListNode node){
		  ((LockDlinkNode)node).locked=true;
	  }
	  public void remove(DListNode node) {
		if  ( ((LockDlinkNode)node).locked){
			System.out.print("the Node is Locked,cannot been removed \n");
			return;
		}
		else{
			super.remove(node);
		}
		  }



public static void main(String[] args){
	LockDList test = new LockDList();
	test.insertFront(15);
	test.insertBack(120);
	
	test.lockNode(test.front());
	test.remove(test.front());
	System.out.print(test.toString());
}
}