package com.example.testproject;

import android.widget.TextView;

public class PageModel {

	 private int index;
	 private String text;
	 public TextView textView;

	 
	 public PageModel(int index) {
	  this.index = index;
	  setIndex(index);
	 }

	 public int getIndex() {
	  return index;
	 }

	 public void setIndex(int index) {
	  this.index = index;
	  setText(index);
	 }

	 public String getText() {
	  return text;
	 }

	 private void setText(int index) {
	  this.text = String.format("Page %s", index);
	 }
	}
