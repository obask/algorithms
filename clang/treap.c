#include <stdio.h>
#include <stdlib.h>


struct Treap {
    long priority;
    long key;
    struct Treap *left;
    struct Treap *right;
};


struct Treap *makeNode(long key, struct Treap *left, struct Treap *right) {
    struct Treap *const node = (struct Treap *) malloc(sizeof(struct Treap));
    node->key = key;
    node->priority = random() % 9000;
    node->left = left;
    node->right = right;
    return node;
}


struct Treap *makeLeaf(long key) {
    return makeNode(key, NULL, NULL);
}

int shift = 0;

void printTree(struct Treap *current) {
    if (current == NULL) {
        printf("#");
        return;
    }
    printf("(%ld _", current->key);
//    printf("(%ld %ld", current->key, current->priority);
    if (current->left != NULL || current->right != NULL) {
        shift += 1;
        printf("\n");
        for (int i = 0; i < shift; i++) {
            printf("     ");
        }
        printTree(current->left);
        printf("\n");
        for (int i = 0; i < shift; i++) {
            printf("     ");
        }
        printTree(current->right);
        shift -= 1;
    }
    printf(")");
}


void print_elements(struct Treap *current) {
    if (current == NULL) {
        return;
    }
    print_elements(current->left);
    printf("%ld ", current->key);
    print_elements(current->right);
}


struct Treap *merge(struct Treap *left, struct Treap *right) {
    if (left == NULL) {
        return right;
    }
    if (right == NULL) {
        return left;
    }
    if (left->priority > right->priority) {
        left->right = merge(left->right, right);
        return left;
    } else {
        right->left = merge(left, right->left);
        return right;
    }
}


void split(struct Treap *current, int pivot, struct Treap **pNewLeft, struct Treap **pNewRight) {
    if (current->right == NULL) {
        *pNewLeft = current;
        *pNewRight = NULL;
        return;
    }
    if (current->left == NULL) {
        *pNewLeft = NULL;
        *pNewRight = current;
        return;
    }
    if (current->key < pivot) {
        struct Treap *newRight;
        struct Treap *rightLeftSubtree;
        split(current->right, pivot, &rightLeftSubtree, &newRight);
        current->right = rightLeftSubtree;
        *pNewLeft = current;
        *pNewRight = newRight;
    } else {
        struct Treap *newLeft;
        struct Treap *leftRightSubtree;
        split(current->left, pivot, &newLeft, &leftRightSubtree);
        current->left = leftRightSubtree;
        *pNewLeft = newLeft;
        *pNewRight = current;
    }
}


int main() {
    struct Treap *n1 = makeLeaf(1);
    struct Treap *n2 = makeLeaf(2);
    struct Treap *n3 = makeLeaf(3);
    struct Treap *n4 = makeLeaf(4);
    struct Treap *n5 = makeLeaf(5);
    struct Treap *n6 = makeLeaf(6);
    struct Treap *n7 = makeLeaf(7);
    struct Treap *n8 = makeLeaf(8);
    struct Treap *n9 = makeLeaf(9);

    struct Treap *temp = merge(n1, merge(n2, merge(n3, merge(n4, merge(n5, merge(n6, merge(n7, merge(n8, n9))))))));

    struct Treap *l;
    struct Treap *r;

    split(temp, 5, &l, &r);

    printf("l = ");
    print_elements(l);
    printf("\n");
    printTree(l);
    printf("\n");
    printf("r = ");
    print_elements(r);
    printf("\n");
    printTree(r);
    printf("\n");

    return 0;
}




