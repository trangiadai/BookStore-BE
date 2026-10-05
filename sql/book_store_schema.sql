CREATE DATABASE IF NOT EXISTS book_store_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE book_store_db;

CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

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

CREATE TABLE product_images (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(512) NOT NULL,
    public_id VARCHAR(255) NOT NULL,
    product_id BIGINT NOT NULL,
    CONSTRAINT fk_product_images_product 
        FOREIGN KEY (product_id) 
        REFERENCES products(id) 
        ON DELETE CASCADE 
        ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE INDEX idx_products_category_id ON products(category_id);
CREATE INDEX idx_product_images_product_id ON product_images(product_id);

INSERT INTO categories (id, name, description) VALUES
(1, 'Technology', 'Books covering software engineering, programming, and IT.'),
(2, 'Science Fiction', 'Speculative fiction involving futuristic science and space travel.'),
(3, 'Self-Help', 'Books focusing on personal growth, habits, and productivity.');

INSERT INTO products (id, name, import_price, selling_price, quantity, description, category_id, created_at) VALUES
(1, 'Clean Code: A Handbook of Agile Software Craftsmanship', 28.50, 42.99, 50, 'Even bad code can function. But if code isn\'t clean, it can bring a development organization to its knees.', 1, NOW()),
(2, 'The Pragmatic Programmer: Your Journey to Mastery', 30.00, 45.00, 35, 'One of the most significant books in software development.', 1, NOW()),
(3, 'Dune (Dune Chronicles, Book 1)', 10.20, 18.99, 100, 'Set on the desert planet Arrakis, Dune is the story of Paul Atreides.', 2, NOW()),
(4, 'Atomic Habits: An Easy & Proven Way to Build Good Habits', 12.00, 21.99, 80, 'Tiny Changes, Remarkable Results.', 3, NOW()),
(5, 'Designing Data-Intensive Applications', 32.00, 49.99, 25, 'An invaluable guide for software engineers and architects.', 1, NOW());

INSERT INTO product_images (url, public_id, product_id) VALUES
('https://res.cloudinary.com/demo/image/upload/v1710000000/bookstore/products/clean_code_front.jpg', 'bookstore/products/clean_code_front', 1),
('https://res.cloudinary.com/demo/image/upload/v1710000001/bookstore/products/clean_code_back.jpg', 'bookstore/products/clean_code_back', 1),
('https://res.cloudinary.com/demo/image/upload/v1710000002/bookstore/products/pragmatic_programmer.jpg', 'bookstore/products/pragmatic_programmer', 2),
('https://res.cloudinary.com/demo/image/upload/v1710000003/bookstore/products/dune_cover.jpg', 'bookstore/products/dune_cover', 3),
('https://res.cloudinary.com/demo/image/upload/v1710000004/bookstore/products/atomic_habits_front.jpg', 'bookstore/products/atomic_habits_front', 4),
('https://res.cloudinary.com/demo/image/upload/v1710000005/bookstore/products/ddia_front.jpg', 'bookstore/products/ddia_front', 5);

ALTER TABLE products 
ADD COLUMN is_deleted TINYINT(1) DEFAULT 0,
ADD COLUMN deleted_at DATETIME NULL DEFAULT NULL;

ALTER TABLE product_images 
ADD COLUMN is_deleted TINYINT(1) DEFAULT 0,
ADD COLUMN deleted_at DATETIME NULL DEFAULT NULL;

ALTER TABLE categories
ADD COLUMN updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;

CREATE TABLE IF NOT EXISTS orphaned_cloudinary_files (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    public_id VARCHAR(255) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'PENDING', -- PENDING, COMPLETED, FAILED_PERMANENTLY, MANUALLY_RESOLVED
    retry_count INT NOT NULL DEFAULT 0,
    last_error TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    INDEX idx_status_retry (status, retry_count)
);

CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'CUSTOMER', -- 'CUSTOMER' or 'ADMIN'
    is_enabled TINYINT(1) NOT NULL DEFAULT 1,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20) UNIQUE,
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_user_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE carts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_carts_account FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE cart_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cart_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_cart_items_cart FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE,
    CONSTRAINT fk_cart_items_product FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT uk_cart_product UNIQUE (cart_id, product_id),
    CONSTRAINT chk_cart_item_quantity CHECK (quantity > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE orders (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    order_code VARCHAR(64) UNIQUE NOT NULL,
    subtotal_amount DECIMAL(12, 2) NOT NULL,
    shipping_fee DECIMAL(12, 2) NOT NULL DEFAULT 0.00,
    total_amount DECIMAL(12, 2) NOT NULL,
    order_status ENUM('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED', 'REFUNDED') NOT NULL DEFAULT 'PENDING',
    payment_status ENUM('UNPAID', 'PAID', 'REFUNDED') NOT NULL DEFAULT 'UNPAID',
    payment_method ENUM('COD', 'BANK_TRANSFER') NOT NULL,
    shipping_full_name VARCHAR(100) NOT NULL,
    shipping_phone_number VARCHAR(20) NOT NULL,
    shipping_address TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_orders_account FOREIGN KEY (account_id) REFERENCES accounts(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE order_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    unit_price DECIMAL(12, 2) NOT NULL,
    quantity INT NOT NULL,
    subtotal DECIMAL(12, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_items_product FOREIGN KEY (product_id) REFERENCES products(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

ALTER TABLE products 
ADD COLUMN total_ratings INT DEFAULT 0,
ADD COLUMN rating_sum INT DEFAULT 0,
ADD COLUMN average_rating DECIMAL(3, 2) DEFAULT 0.00;

CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    order_item_id BIGINT NOT NULL UNIQUE,
    product_id BIGINT NOT NULL,
    account_id BIGINT NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reviews_order_item FOREIGN KEY (order_item_id) REFERENCES order_items(id),
    CONSTRAINT fk_reviews_product FOREIGN KEY (product_id) REFERENCES products(id),
    CONSTRAINT fk_reviews_account FOREIGN KEY (account_id) REFERENCES accounts(id)
);

