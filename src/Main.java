import bst.Treap;

public class Main {

    public static void checkTreap() {
        System.out.println("checkTreap:");

        Treap t0 = Treap.create(0);
        Treap t2 = Treap.create(2);
        Treap t4 = Treap.create(4);

        Treap res = Treap.merge(Treap.merge(t0, t2), t4);
        Treap tmp = res.insert(6).insert(1).insert(3);

        System.out.println(tmp);
    }

    public static void main(String[] args) {
        checkTreap();
        System.out.println("DONE");
    }

}
