
public class Dlist {
	DlinkNode head;
	int size;
	//void construtor 
	public Dlist(){
		head=new DlinkNode();
		size=0;
		head.prev=head;
		head.next=head;
	}
	//constructor for the t
	public void InsertFront(int n,int red,int green,int blue){
		DlinkNode node=new DlinkNode(n,red,green,blue);
		if (size==0){
			head.next=node;
			head.prev=node;
			node.next=head;
			node.prev=head;
		}
		else{
			node.next=head.next;
			head.next.prev=node;
			head.next=node;
			node.prev=head;
		}
	size++;
	}

	public void insertEnd(int n,int red,int green, int blue){
		DlinkNode node = new DlinkNode(n,red,green,blue);
		if (size==0)	{
			head.prev=node;
			node.prev=head;
			head.next=node;
			node.next=head;}
		
		else{
			head.prev.next=node;
			node.next=head;
			node.prev=head.prev;
			head.prev=node;
	}
		size++;
	}
	
	public void remove(DlinkNode current){
		current.next.prev=current.prev;
		current.prev.next=current.next;
	}
	public String toString(){
		DlinkNode current=head.next;
		String a="the Double-linked list is:\n";
		while(current!=head){
			a+=current.count+"-->";
		}
		return a;
	}
	}