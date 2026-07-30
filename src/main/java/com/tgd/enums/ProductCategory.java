package com.tgd.enums;

public enum ProductCategory {

	FICTION("Fiction", "fiction"), NON_FICTION("Non-Fiction", "non-fiction"),
	SCIENCE_FICTION("Science Fiction", "science-fiction"), FANTASY("Fantasy", "fantasy"),
	TECHNOLOGY("Technology & Software", "technology"), BUSINESS("Business & Economics", "business"),
	SELF_HELP("Self-Help & Personal Growth", "self-help"), BIOGRAPHY("Biography & Memoir", "biography"),
	HISTORY("History", "history"), CHILDREN("Children's Books", "children"),
	COMICS_GRAPHIC_NOVELS("Comics & Graphic Novels", "comics-graphic-novels");

	private final String displayName;
	private final String slug;

	private ProductCategory(String displayName, String slug) {
		this.displayName = displayName;
		this.slug = slug;
	}

	public static ProductCategory fromString(String text) {
		if (text == null || text.isBlank()) {
			return null;
		}

		String cleaned = text.trim();

		for (ProductCategory category : ProductCategory.values()) {
			if (category.name().equalsIgnoreCase(cleaned)) {
				return category;
			}
			// Check normalized name (converts "SELF-HELP" -> "SELF_HELP")
			if (category.name().equalsIgnoreCase(cleaned.replace('-', '_').replace(' ', '_'))) {
				return category;
			}
			// Check slug matching (e.g. "self-help")
			if (category.getSlug().equalsIgnoreCase(cleaned)) {
				return category;
			}
		}

		throw new IllegalArgumentException("No enum constant matching category: " + text);
	}

	public String getDisplayName() {
		return displayName;
	}

	public String getSlug() {
		return slug;
	}

}