CREATE DATABASE IF NOT EXISTS book_store_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE book_store_db;
-- 1. Create categories table
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 2. Create products table
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    import_price DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    selling_price DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    quantity INT NOT NULL DEFAULT 0,
    description TEXT,
    category_id BIGINT NOT NULL,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Foreign Key referencing categories table
    CONSTRAINT fk_products_category 
        FOREIGN KEY (category_id) 
        REFERENCES categories(id) 
        ON DELETE RESTRICT 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 3. Create product_images table
CREATE TABLE product_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(512) NOT NULL,
    public_id VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    
    -- Foreign Key referencing products table
    CONSTRAINT fk_product_images_product 
        FOREIGN KEY (product_id) 
        REFERENCES products(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Performance Indexes
CREATE INDEX idx_products_category_id ON products(category_id);
CREATE INDEX idx_product_images_product_id ON product_images(product_id);

-- 1. Seed categories
INSERT INTO categories (id, name, description) VALUES
(1, 'Technology', 'Books covering software engineering, programming, and IT.'),
(2, 'Science Fiction', 'Speculative fiction involving futuristic science and space travel.'),
(3, 'Self-Help', 'Books focusing on personal growth, habits, and productivity.');

-- 2. Seed products
INSERT INTO products (id, name, import_price, selling_price, quantity, description, category_id, created_at) VALUES
(1, 'Clean Code: A Handbook of Agile Software Craftsmanship', 28.50, 42.99, 50, 'Even bad code can function. But if code isn\'t clean, it can bring a development organization to its knees.', 1, NOW()),
(2, 'The Pragmatic Programmer: Your Journey to Mastery', 30.00, 45.00, 35, 'One of the most significant books in software development.', 1, NOW()),
(3, 'Dune (Dune Chronicles, Book 1)', 10.20, 18.99, 100, 'Set on the desert planet Arrakis, Dune is the story of Paul Atreides.', 2, NOW()),
(4, 'Atomic Habits: An Easy & Proven Way to Build Good Habits', 12.00, 21.99, 80, 'Tiny Changes, Remarkable Results.', 3, NOW()),
(5, 'Designing Data-Intensive Applications', 32.00, 49.99, 25, 'An invaluable guide for software engineers and architects.', 1, NOW());

-- 3. Seed product_images
INSERT INTO product_images (url, public_id, product_id) VALUES
('https://res.cloudinary.com/demo/image/upload/v1710000000/bookstore/products/clean_code_front.jpg', 'bookstore/products/clean_code_front', 1),
('https://res.cloudinary.com/demo/image/upload/v1710000001/bookstore/products/clean_code_back.jpg', 'bookstore/products/clean_code_back', 1),
('https://res.cloudinary.com/demo/image/upload/v1710000002/bookstore/products/pragmatic_programmer.jpg', 'bookstore/products/pragmatic_programmer', 2),
('https://res.cloudinary.com/demo/image/upload/v1710000003/bookstore/products/dune_cover.jpg', 'bookstore/products/dune_cover', 3),
('https://res.cloudinary.com/demo/image/upload/v1710000004/bookstore/products/atomic_habits_front.jpg', 'bookstore/products/atomic_habits_front', 4),
('https://res.cloudinary.com/demo/image/upload/v1710000005/bookstore/products/ddia_front.jpg', 'bookstore/products/ddia_front', 5);


