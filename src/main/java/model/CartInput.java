package model;

import java.io.Serializable;

public class CartInput implements Serializable {
	 	private int wpco500g;
	    private int wpco1kg;
	    private int hpco500g;
	    private int hpco1kg;
	    private int creatine500g;

	    public CartInput(int wpco500g, int wpco1kg, int hpco500g, int hpco1kg, int creatine500g) {
	        this.wpco500g = wpco500g;
	        this.wpco1kg = wpco1kg;
	        this.hpco500g = hpco500g;
	        this.hpco1kg = hpco1kg;
	        this.creatine500g = creatine500g;
	    }

	    public int getWpco500g() { return wpco500g; }
	    public int getWpco1kg() { return wpco1kg; }
	    public int getHpco500g() { return hpco500g; }
	    public int getHpco1kg() { return hpco1kg; }
	    public int getCreatine500g() { return creatine500g; }
}
