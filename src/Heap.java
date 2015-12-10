package com.github.obask;

import java.util.ArrayList;
import java.util.List;

public class Heap<Elem extends Comparable<Elem>> {

    public List<Elem> heap = new ArrayList<>();

    public int size() {
        return heap.size();
    }

//    # 'heap' is a heap at all indices >= startPos, except possibly for pos.  pos
//    # is the index of a leaf with a possibly out-of-order value.  Restore the
//    # heap invariant.
    private void _siftDown(int startPos, int pos0) {
        int pos = pos0;
        Elem newItem = heap.get(pos);
//        # Follow the path to the root, moving parents down until finding a place
//        # new item fits.
        while (pos > startPos) {
            int parentPos = (pos - 1) / 2;
            Elem parent = heap.get(parentPos);
            if (newItem.compareTo(parent) < 0) {
                heap.set(pos, parent);
                pos = parentPos;
            } else
                break;
        }
        heap.set(pos, newItem);
    }

    private void _siftUp(int pos) {
        int endPos = heap.size();
        int startPos = pos;
        Elem newItem = heap.get(pos);
        // # Bubble up the smaller child until hitting a leaf.
        int childPos = 2*pos + 1;
        while (childPos < endPos) {
            // # Set childPos to index of smaller child.
            int rightPos = childPos + 1;
            if (rightPos < endPos && (heap.get(childPos).compareTo(heap.get(rightPos)) >= 0)) {
                childPos = rightPos;
            }
            heap.set(pos, heap.get(childPos));
            pos = childPos;
            childPos = 2*pos + 1;
        }
        // # The leaf at pos is empty now.  Put newitem there, and bubble it up
        // # to its final resting place (by sifting its parents down).
        heap.set(pos, newItem);
        _siftDown(startPos, pos);
    }

    public void push(Elem item) {
    // "Push item onto heap, maintaining the heap invariant."
        heap.add(item);
        this._siftDown(0, this.size() - 1);
    }



    public Elem pop() {
    // "Pop the smallest item off the heap, maintaining the heap invariant."

        Elem lastElement = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            Elem result = heap.get(0);
            heap.set(0, lastElement);
            this._siftUp(0);
            return result;
        } else {
            return lastElement;
        }

    }


}
