/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
/**
 * LinkedBinarySearchTree for tree traversal lab
 * @author Lachlan Plant
 * @param <E>
 */
public class LinkedBinarySearchTree<E extends Comparable> implements Iterable<E>{
    
    private class Node<E>{
        E elem;
        Node<E> parent;
        Node<E> left;
        Node<E> right;
        public Node(E e,Node<E> p, Node<E> l, Node<E> r){
            elem = e;
            parent= p;
            left = l;
            right = r;
        }
    }
    
    private Node<E> root;
    private int size;
    
    /**
     *
     */
    public LinkedBinarySearchTree(){
        root = null;
        size = 0;
    }
    
    /**
     * Adds elem into BST
     * @param elem
     * @return
     */
    public boolean add(E elem){
        if(root == null){
            root = new Node<>(elem, null, null, null);
            size ++;
            return true;
        }
        else{
            root = insert(elem, root, null);
            return true;
        }
    }
    
    /**
     * Recursive BST insertion
     * @param elem
     * @param curr
     * @param from
     * @return
     */
    private Node<E> insert(E elem, Node<E> curr, Node<E> from){
        if(curr == null){
            curr = new Node<>(elem, from, null, null);
            size ++;
            return curr;
        }
        if( elem.compareTo(curr.elem)<0){
            curr.left = insert(elem, curr.left, curr);
        }
        if( elem.compareTo(curr.elem)>0){
            curr.right = insert(elem, curr.right, curr);
        }
        return curr;
    }
    

    
    /*****************************************************************
     *
     * Recursive Printing Functions
     *
     *
     *****************************************************************/
    
    /**
     * Caller method for preorder recursive printing
     */
    public void printPreorderRecursive(){
        System.out.print("Recursive Preorder Printing: ");
        preorderRecursive(root);
    }
    
    /**
     * preorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void preorderRecursive(Node<E> curr){
        if (curr == null) {
            return;
        } else {
            System.out.print(curr.elem + ", ");
            preorderRecursive(curr.left);
            preorderRecursive(curr.right);
        }
    }
    
    /**
     * Caller method for inorder recursive printing
     */
    public void printInorderRecursive(){
        System.out.print("Recursive Inorder Printing: ");
        inorderRecursive(root);
    }
    
    /**
     * inorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void inorderRecursive(Node<E> curr){
        if (curr == null) {
            return;
        } else {
            inorderRecursive(curr.left);
            System.out.print(curr.elem + ", ");
            inorderRecursive(curr.right);
        }
    }
    
    
    /**
     * Caller method for postorder recursive printing
     */
    public void printPostorderRecursive(){
        System.out.print("Recursive Postorder Printing: ");
        postorderRecursive(root);
    }
    
    /**
     * postorder tree traversal, prints(curr.elem + ", ")
     * @param curr
     */
    private void postorderRecursive(Node<E> curr){
        if (curr == null) {
            return;
        } else {
            postorderRecursive(curr.left);
            postorderRecursive(curr.right);
            System.out.print(curr.elem + ", ");
        }
    }

    
    
     /*****************************************************************
     *
     * Iterator Functions
     *
     *
     *****************************************************************/
    
    
    public Iterator iterator(){
        return new InorderIterator();
    }
    
    public Iterator inorderIterator(){
        return new InorderIterator();
    }
    
    public Iterator preorderIterator(){
        return new PreorderIterator();
    }
    

    
     /*****************************************************************
     *
     * Iterators 
     *
     *
     *****************************************************************/ 
    
    
    
    
    /**
     * Tree Iterator using preorder traversal for ordering
     */
    private class PreorderIterator implements Iterator<E>{
        Node<E> next;
        
        public PreorderIterator(){
            //Implement Here
            next = root;

        }
        
        public boolean hasNext(){
            //Implement Here
            if(next == null){
                return false;
            }
            return true;
        }
        
        public E next(){
            //Implement Here
            E output = next.elem;
        if(next.left!=null){
            next = next.left;
            return output;
        }
        else{
            if(next.right!=null){
                next = next.right;
                return output;
            }
            else{
                Node<E> parent = next.parent;
                Node<E> child = next;
                while(parent != null && (parent.right == child || parent.right == null)){
                    child = parent;
                    parent = parent.parent;
                }
                if(parent == null){
                    next = null;
                    return output;
                }
                else{
                    next = parent.right;
                    return output;
                }
            }
        }

            

        }
        
        public void remove(){
            // not implemented
        }
    }
    
    /**
     * Tree Iterator using inorder traversal for ordering
     */
    private class InorderIterator implements Iterator<E>{
        
        Node<E> next;
        
        public InorderIterator(){
            //Implement Here
            next = root; 
            while(next.left != null){
                next = next.left;
            }

        }
        
        public boolean hasNext(){
            //Implement Here
            if(next == null){
                return false;
            }
            return true; 
        }
        
        public E next(){
            //Implement Here
            E output = next.elem;
            if(next.right != null){
                next = next.right;
                while (next.left != null){
                    next = next.left;
                }
                return output;
              }
              else {
                while(true){ 
                   if(next.parent == null){
                        next = null;
                        return output;
                   } 
                   if(next.parent.left == next){
                        next = next.parent;
                        return output;
                   }  
                   next = next.parent;
                }  
            } 
              
        }
        
        public void remove(){
            // not implemented

        }
    }
}
