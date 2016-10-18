/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tf.idf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Vector;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author fitri ratna
 */
public class TFIDF {

    public static void main(String[] args) throws IOException {
        //input();
        idf();
    }
//
//    private static void input() throws IOException {
//        BufferedReader x = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("Masukan website : ");
//        String website = x.readLine();
//        parsing(website);
//    }
//
//    private static void parsing(String url) throws IOException {
//        url = "http://" + url;
//        Document doc = Jsoup.connect(url).timeout(0).get();
//        Elements text = doc.select("body");
//        String teks = text.text();
//        teks = teks.replaceAll("[(-+.^:,'|&?!)]", "").replaceAll("yang", "").replaceAll("dengan", "").replaceAll("dan", "").replaceAll("dari", "").replaceAll("ke","");
//        String[] kata = teks.split(" ");
//
//        //-----------------------------------
//        String[] kata1 = new String[kata.length];
//        for (int i = 0; i < kata.length; i++) {
//            kata1[i] = kata[i];
//           
//        }
//
//        Vector<String> index_kata = new <String>Vector();
//        Vector<Integer> hitung_kata = new <Integer>Vector();
//        for (int i = 0; i < kata1.length; i++) {
//            int hitung = 0;
//            for (int j = 1; j < kata.length; j++) {
//                if (kata1[i].equals(kata[j])) {
//                    hitung++;
//                }
//            }
//            if (index_kata.size() == 0) {
//                index_kata.add(kata1[i]);
//                hitung_kata.add(hitung);
//            } else {
//                int ada = 0;
//                for (int j = 0; j < index_kata.size(); j++) {
//                    if (kata1[i].equals(index_kata.get(j))) {
//                        ada++;
//                    }
//                }
//
//                if (ada == 0) {
//                    index_kata.add(kata1[i]);
//                    hitung_kata.add(hitung);
//                }
//            }
//        }
//
////        for (int i = 0; i < kata1.length; i++) {
////            System.out.println(kata1[i]);
////        }
//
//        System.out.println(kata.length + "<>" + index_kata.size());
//        Hashtable tabel_kata = new Hashtable();
//        for (int i = 0; i < index_kata.size(); i++) {
//            System.out.println(index_kata.get(i) + " = " + ((double)(hitung_kata.get(i)+1)/kata.length));
//            tabel_kata.put(index_kata.get(i),hitung_kata.get(i));
//        }
//        
//    } 
//    

    public static void idf() {
        Vector<String> dokumen = new <String>Vector();
        String d1 = "Selamat Datang di Program Studi Sistem Informasi Fakultas Sains dan Teknologi Universitas Islam Negeri Sunan Ampel Surabaya "; //17
        String d2 = "Selamat Datang di Program Studi Sistem Informasi"; //7
        String d3 = "Selamat Datang di UIN Sunan Ampel Surabaya mahasiswa program studi sistem informasi angkatan 2014 dan angkatan 2015 dan angkatan 2016"; //20

        dokumen.add(d1);
        dokumen.add(d2);
        dokumen.add(d3);

//        Vector<Integer> index_dokumen_terbesar = new <Integer>Vector();
//
//        for (int i = 0; i < dokumen.size(); i++) {
//            String[] tes = dokumen.get(i).split(" ");
//            index_dokumen_terbesar.add(tes.length);
//        }
//
//        int terbesar = 0;
//        for (int i = 0; i < index_dokumen_terbesar.size(); i++) {
//            for (int j = 1; j < index_dokumen_terbesar.size(); j++) {
//                if (index_dokumen_terbesar.get(i) > index_dokumen_terbesar.get(j)) {
//                    if (terbesar < index_dokumen_terbesar.get(i)) {
//                        terbesar = index_dokumen_terbesar.get(i);
//                    }
//
//                } else if (index_dokumen_terbesar.get(i) < index_dokumen_terbesar.get(j)) {
//                    if (terbesar < index_dokumen_terbesar.get(j)) {
//                        terbesar = index_dokumen_terbesar.get(j);
//                    }
//                }
//            }
//        }
//        System.out.println(terbesar);
//
//        int[][] kata_kata = new int[dokumen.size()][terbesar];

        Vector<String> index = new <String>Vector();
        for (int i = 0; i < dokumen.size(); i++) {
            if (index.size() == 0) {
                String[] tes2 = dokumen.get(i).split(" ");
                for (int j = 0; j < tes2.length; j++) {
                    index.add(tes2[j]);
                }
                
//                for (int j = 0; j < index.size(); j++) {
//                    System.out.println(index.get(j));
//                }

            } else {
                
                String[] tes3 = dokumen.get(i).split(" ");
                for (int j = 0; j < tes3.length; j++) {
                    int isi = 0;
                    for (int k = 0; k < index.size(); k++) {
                        if (!tes3[j].equalsIgnoreCase(index.get(k))) {
                            //System.out.println("Banding " + tes3[j] + "/" + index.get(k) );
                            isi++;
                            //System.out.println(isi);
                        }
                    }
                    //System.out.println("isi = " + isi);
                    //System.out.println("index.size = " + index.size());
                    if (isi == index.size()) {
                        index.add(tes3[j]);
                    }
                }
            }
            
      
        }
      
        //hitung semua
        int[][] hitung_kata = new int[dokumen.size()][index.size()];
        for (int i = 0; i < dokumen.size(); i++) {
            String[] kata = dokumen.get(i).split(" ");
            for (int j = 0; j < index.size(); j++) {
                for (int k = 0; k < kata.length; k++) {
                    if (index.get(j).equalsIgnoreCase(kata[k])) {
                       // System.out.println(index.get(j) + " sama " + kata[k] +" == dok  "+(i+1));
                        hitung_kata[i][k] = 1;
                    }else{
                        hitung_kata[i][k] = 0;
                    }
                }
            }
        }
        
      
        for (int i = 0; i < hitung_kata.length; i++) {
            System.out.println(index.get(i));
            for (int j = 0; j < hitung_kata[i].length; j++) {
                System.out.print(hitung_kata[i][j]);
            }
            System.out.println("");
        }

      
    }
}
