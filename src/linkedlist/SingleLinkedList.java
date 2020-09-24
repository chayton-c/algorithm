package linkedlist;

/**
 * @author hood  2020/7/7
 */
public class SingleLinkedList {
    private HeroNode head = new HeroNode(0, "", "");
    private HeroNode tail = head;

    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                temp.setNext(heroNode);
                return;
            }
            temp = temp.getNext();
        }
    }

    public void showDown() {
        HeroNode temp = head;
        while (true) {
            if (temp.getNext() == null) {
                return;
            }
            System.out.println(temp.getNext().toString());
        }
    }

    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(new HeroNode(1, "akagi", "akagi"));
        singleLinkedList.add(new HeroNode(2, "kaga", "kaga"));
        singleLinkedList.showDown();
    }

}
