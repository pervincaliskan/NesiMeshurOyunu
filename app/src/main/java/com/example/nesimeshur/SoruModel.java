package com.example.nesimeshur;

public class SoruModel {



        private String soru, secenek1, secenek2, secenek3;
        private int cevapHangisi;

        public SoruModel(String soru, String secenek1, String secenek2, String secenek3, int kacinciSoruda) {
            this.soru = soru;
            this.secenek1 = secenek1;
            this.secenek2 = secenek2;
            this.secenek3 = secenek3;
            this.cevapHangisi = kacinciSoruda;
        }

        public String getQuestion() {
            return soru;
        }

        public void setQuestion(String question) {
            this.soru = question;
        }

        public String getOption1() {
            return secenek1;
        }

        public void setOption1(String option1) {
            this.secenek1 = option1;
        }

        public String getOption2() {
            return secenek2;
        }

        public void setOption2(String option2) {
            this.secenek2 = option2;
        }

        public String getOption3() {
            return secenek3;
        }

        public void setOption3(String option3) {
            this.secenek3 = option3;
        }

        public int getCorrectAnsNo() {
            return cevapHangisi;
        }

        public void setCorrectAnsNo(int correctAnsNo) {
            this.cevapHangisi = correctAnsNo;
        }
    }

