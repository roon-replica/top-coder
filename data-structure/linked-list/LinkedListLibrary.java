import java.util.LinkedList;

public class LinkedListLibrary {
    public static void main(String[] args) {
        LinkedList<Info> list = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(new Info("content" + i, "type" + i));
        }

        System.out.println("first = " + list.getFirst());
        list.addFirst(new Info("new content", "new type"));

        System.out.println(list);

        list.remove(2);
        System.out.println(list);

    }

    private static class Info {
        String content;
        String contentType;

        Info(String content, String contentType) {
            this.content = content;
            this.contentType = contentType;
        }

        @Override
        public String toString() {
            return "{content:" + content + ", type: " + contentType + "}";
        }
    }
}
