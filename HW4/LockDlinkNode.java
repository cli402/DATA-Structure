package list;

public class LockDlinkNode extends DListNode {
	protected boolean locked;
	LockDlinkNode(Object o,DListNode prev,DListNode next){
		super(o,prev,next);
		locked=false;
	}
}
