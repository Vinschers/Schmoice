package br.unicamp.cotuca.schmoice;

import java.util.ArrayList;

public class Arvore {
    private No raiz;
    private No faseAtual;
    private ArrayList<Integer> caminho;
    public Arvore() {
        raiz = null;
        faseAtual = null;
        caminho = new ArrayList<Integer>();
    }
    public Arvore(Arvore arvore) {
        this.raiz = arvore.raiz;
        this.faseAtual = arvore.faseAtual;
        this.caminho = arvore.caminho;
        faseAtual = raiz;
        for (int c : caminho) {
            if (c == 1) {
                faseAtual = faseAtual.getDireita();
            } else {
                faseAtual = faseAtual.getEsquerda();
            }
        }
    }
    public boolean equals(Object outra) {
        if (outra == this)
            return true;
        if (outra == null)
            return false;
        if (outra.getClass() != this.getClass())
            return false;
        Arvore a = (Arvore) outra;
        if (!a.raiz.equals(this.raiz))
            return false;
        if (!a.caminho.equals(this.caminho))
            return false;
        if (!a.faseAtual.equals(this.faseAtual))
            return false;
        return true;
    }
    public int hashCode() {
        int ret = 3;
        ret = ret * 2 + raiz.hashCode();
        ret = ret * 3 + faseAtual.hashCode();
        ret = ret * 5 + caminho.hashCode();
        return ret;
    }
    public Fase getFaseAtual() {
        return faseAtual.getFase();
    }
    public Fase getFase(Fase fase) throws Exception {
        No atual = raiz;
        while (!atual.getFase().equals(fase)) {
            if (fase == null) {
                throw new Exception("Fase <" + fase.toString() + "> nao existe!!");
            } else if (fase.compareTo(atual.getFase()) > 0) {
                atual = atual.getDireita();
            } else if (fase.compareTo(atual.getFase()) < 0) {
                atual = atual.getEsquerda();
            }
        }
        return atual.getFase();
    }
    public void adicionar(Fase fase) throws Exception{
        No atual = raiz;
        while (atual != null) {
            if (fase.compareTo(atual.getFase()) > 0) {
                atual = atual.getDireita();
            } else if (fase.compareTo(atual.getFase()) < 0) {
                atual = atual.getEsquerda();
            } else {
                throw new Exception("Fase <" + fase.toString() + "> ja existe!");
            }
        }
        atual = new No(fase, null, null);
    }
    public void remover(Fase fase) throws Exception {
        No atual = raiz;
        while (!atual.getEsquerda().getFase().equals(fase) && !atual.getDireita().getFase().equals(fase)) {
            if (fase == null) {
              throw new Exception("Fase <" + fase.toString() + "> nao existe!!");
            } else if (fase.compareTo(atual.getFase()) > 0) {
                atual = atual.getDireita();
            } else if (fase.compareTo(atual.getFase()) < 0) {
                atual = atual.getEsquerda();
            }
        }
        if (atual.getEsquerda() == null || atual.getDireita() == null) {
            if (atual.getEsquerda().getFase().equals(fase)) {
                atual.setEsquerda(null);
            } else {
                atual.setDireita(null);
            }
            return;
        }
        No aux = atual;
        while (atual.getDireita().getDireita() != null)
            atual = atual.getDireita();
        aux = atual.getDireita();
        atual.setDireita(null);
    }
    public void atualizar(Fase fase) throws Exception {
        remover(fase);
        adicionar(fase);
    }
    /*public String getCaminho() {
        String s = "";
        for(int c : caminho) {
            s += c + "";
        }
        return s;
    }*/
    public ArrayList<Integer> getCaminho() {
        return caminho;
    }

    public void setCaminho(ArrayList<Integer> c) {
        caminho = c;
    }
    public void setCaminho(String s) {
        caminho = new ArrayList<Integer>();
        for (char c : s.toCharArray()) {
            caminho.add((int)c-42);
        }
    }
    public void adicionarAoCaminho(int s) {
        caminho.add(s);
    }
}