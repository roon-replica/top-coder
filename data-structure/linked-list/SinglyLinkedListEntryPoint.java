public class SinglyLinkedListEntryPoint {
    public static void main(String[] args) {
        // integer list
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.addFirst(i);
        }

        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.removeFirst());

        System.out.println(list);
        list.addFirst(1);
        System.out.println(list);

        // string list
        SinglyLinkedList<String> list2 = new SinglyLinkedList<>();
        for (char ch = 'a'; ch <= 'd'; ch++) {
            list2.addFirst("" + ch);
        }
        System.out.println(list2);

        // add(int idx, T content) 테스트
        list2.add(0, "add-first");
        list2.add(2, "add-2");
        list2.add(list2.size(), "add-last");
        list2.addFirst("add-first-again");
        list2.add(list2.size(), "add-last-again");
        System.out.println(list2);

        list2.removeFirst();
        System.out.println(list2+" "+list2.size());

    }
}
