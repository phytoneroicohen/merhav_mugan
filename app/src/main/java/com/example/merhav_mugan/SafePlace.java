package com.example.merhav_mugan;

import java.util.List;

public class SafePlace {
 List<Double> cordinate;
 int max_pcc; 

 public void setCordinate(List<Double> cordinate) {
  this.cordinate = cordinate;
 }

 public void setMax_pcc(int max_pcc) {
  this.max_pcc = max_pcc;
 }

 public SafePlace(List<Double> cordinate, int max_pcc) {
  this.cordinate = cordinate;
  this.max_pcc = max_pcc;
 }

 public List<Double> getCordinate() {
  return cordinate;
 }

 public int getMax_pcc() {
  return max_pcc;
 }
}
