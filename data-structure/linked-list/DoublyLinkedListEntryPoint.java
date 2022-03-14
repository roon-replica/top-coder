public class DoublyLinkedListEntryPoint {
    public static void main(String[] args) {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        for (char ch = '1'; ch <= '4'; ch++) {
            list.addFirst("" + ch);
        }

        System.out.println(list);

        list.add(0, "add-first");
        list.add(1, "add-2nd");
        list.add(list.size(), "add-last");
        list.add(2, "add-3rd");
        System.out.println(list);

        System.out.println(list.remove(1));
        System.out.println(list);

//        System.out.println(list.remove(2));
        String removed = list.remove(2);
        System.out.println(list + ", size = " + list.size());

        System.out.println(list.removeFirst());
        System.out.println(list + ", size = " + list.size());

        //should throw IndexOutOfBoundsException
        //System.out.println(list.remove(list.size()));

        System.out.println("============================================================");
        DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
        list2.addFirst(1);
        System.out.println(list2);

        list2.addFirst(2);
        list2.addFirst(3);
        System.out.println(list2);
        System.out.println(list2.getFirst() + " " + list2.getLast());

    }
}
