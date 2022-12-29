package com.example.SpringWebsite.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
public class BookEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String title;

	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Review> reviewList;



	private String releaseDate;


	private String category;


	private String description;


	private Integer page;


	private String image;

	private float rate;

	private double price;
	@Column
	private Float bookRate = Float.valueOf(0);



	public Float getBookRate() {
		return bookRate;
	}

	public void setBookRate(Float bookRate) {
		this.bookRate = bookRate;
	}

	public BookEntity(Integer id, String title, List<Review> reviewList, String releaseDate, String category, String description, Integer page, String image, float rate, double price, Float bookRate, List<Review> reviewList1, String author) {
		this.id = id;
		this.title = title;
		this.reviewList = reviewList;
		this.releaseDate = releaseDate;
		this.category = category;
		this.description = description;
		this.page = page;
		this.image = image;
		this.rate = rate;
		this.price = price;
		this.bookRate = bookRate;
		this.reviewList = reviewList1;
		this.author = author;
	}

	public List<Review> getReviewList() {
		return reviewList;
	}

	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	private String author;

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public BookEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookEntity(Integer id, String title, String releaseDate, String category, String description, Integer page,
					  String image, float rate, double price, String author) {
		super();
		this.id = id;
		this.title = title;
		this.releaseDate = releaseDate;
		this.category = category;
		this.description = description;
		this.page = page;
		this.image = image;
		this.rate = rate;
		this.price = price;
		this.author = author;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookEntity{" +
				"id=" + id +
				", title='" + title + '\'' +
				", releaseDate='" + releaseDate + '\'' +
				", category='" + category + '\'' +
				", description='" + description + '\'' +
				", page=" + page +
				", image='" + image + '\'' +
				", author='" + author + '\'' +
				'}';
	}

	@Transient
	public String getBookCoverPath(){
		if(image == null || id == null) return null;
		return "/BookCover/" + id + "/" + image;
	}
}
