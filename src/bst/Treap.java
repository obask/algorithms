package bst;

import java.util.Random;

public class Treap {

// private:

    private static final Random GENERATOR = new Random();

    private final Treap left;
    private final Treap right;
    private final int priority;
    private final int key;

    private Treap(int x, int y, Treap left, Treap right) {
        this.key = x;
        this.priority = y;
        this.left = left;
        this.right = right;
    }

    private Treap cloneNode(Treap left, Treap right) {
        return new Treap(this.key, this.priority, left, right);
    }


// public:

    public static Treap create(int key) {
        int prior = GENERATOR.nextInt();
        return new Treap(key, prior, null, null);
    }

    public static Treap merge(Treap l, Treap r) {
        if (l == null) {
            return r;
        }
        if (r == null) {
            return l;
        }
        if (l.priority > r.priority) {
            return l.cloneNode(l.left, merge(l.right, r));
        } else {
            return r.cloneNode(merge(l, r.left), r.right);
        }
    }

    public Treap[] split(int pivot) {
        if (this.key < pivot) {
            Treap[] tuple = (this.right == null)
                    ? new Treap[] {null, null}
                    : this.right.split(pivot);
            Treap l1 = this.cloneNode(this.left, tuple[0]);
            return new Treap[] {l1, tuple[1]};
        } else if (this.key > pivot) {
            Treap[] tuple = (this.left == null)
                    ? new Treap[] {null, null}
                    : this.left.split(pivot);
            Treap r1 = this.cloneNode(tuple[1], this.right);
            return new Treap[] {tuple[0], r1};
        } else {
            return new Treap[] {this.left, this.right};
        }
    }

    public Treap insert(int key) {
        Treap[] tuple = this.split(key);
        return merge(tuple[0], merge(create(key), tuple[1]));
    }

    public String toString() {
        String ll = (this.left == null) ? "" : this.left.toString();
        String rr = (this.right == null) ? "" : right.toString();
        return "(" + ll + " " + this.key + "/" + this.priority + " " + rr + ")";
    }

}
