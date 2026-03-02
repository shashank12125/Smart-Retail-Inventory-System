# 🛒 Smart Retail Inventory & Billing Management System

A full-stack Retail Inventory and Billing Management System built using Spring Boot, JPA (Hibernate), Thymeleaf, and MySQL.

This system allows product management, multi-product billing with transaction safety, soft delete implementation, and bill history tracking.
<hr>
<h3><b>🚀Features</b></h3>

📦 <b>Product Management</b>
    
       ● Add new products
                
       ● Edit product details
                
       ● Soft delete (deactivation instead of physical deletion)
        
       ● View active product list

🧾 <b>Billing Module</b>

       ● Multi-product billing in a single transaction
        
       ● Automatic subtotal & total calculation
        
       ● Automatic stock reduction
        
       ● Transaction management using @Transactional
        
       ● Rollback on failure (ensures data consistency)

📊 <b>Bill History</b>

       ● View all previous bills
        
       ● View detailed bill items
        
       ● Persistent billing records stored relationally

🧠 <b>Key Concepts Implemented</b>

● MVC Architecture (Controller → Service → Repository)

● JPA Entity Relationships

        ● OneToMany (Bill → BillItem)
        
        ● ManyToOne (BillItem → Product)

● Transaction Management (@Transactional)

● Soft Delete (active flag instead of physical deletion)

● Relational Database Integrity

● Exception Handling

● Form Binding using Thymeleaf

<hr>

🛠️ Tech Stack

● <b>Backend:</b> Spring Boot 3

● <b>ORM:</b> Spring Data JPA (Hibernate)

● <b>Frontend:</b> Thymeleaf

● <b>Database:</b> MySQL

● <b>Build Tool:</b> Maven

● <b>Version Control:</b> Git & GitHub

🏗️ <b>Project Architecture</b>

        User (Browser)
             ↓
        Controller
             ↓
        Service (Business Logic + Transactions)
             ↓
        Repository
             ↓
        MySQL Database

🗃️ <b>Database Schema Overview</b>

<b>Product</b>

       ● id
        
       ● name
        
       ● category
        
       ● price
        
       ● quantity
        
       ● active (soft delete flag)

<b>Bill</b>

       ● id
        
       ● billDate
        
       ● totalAmount

<b>BillItem</b>

       ● id
        
       ● bill_id (FK)
        
       ● product_id (FK)
        
       ● quantity
        
       ● subtotal

🔄 <b>System Flow</b>

1. Admin adds products to inventory.

2. Customer billing is performed.

3. System:

    ● Validates stock
    
    ● Calculates subtotal & total
    
    ● Reduces stock
    
    ● Saves bill & bill items

4. All operations run inside a transaction.

5. Bills can be viewed anytime in Bill History.

⚙️ <b>How to Run the Project<b/>

1. Clone repository:
       git clone https://github.com/shashank12125/Smart-Retail-Inventory-System
2. Open in Intelij/ any IDE.
3. Configure MySQL in application.properties.
4. Run:
    InventoryApplication.java
5. Open in Browser:
       http://localhost:8080

🔒 <b>Transaction Safety</b>

Billing operations are wrapped inside @Transactional.

If:

    ● Stock is insufficient
    
    ● Any runtime exception occurs

Then:

    ● All changes are rolled back
    
    ● Database remains consistent

📌 <b>Why Soft Delete?</b>

Instead of deleting products physically, an active flag is used to preserve billing history and maintain referential integrity.

🔮 <b>Future Enhancements</b>

● User authentication (Admin / Staff roles)

● Sales dashboard

● Low stock alerts

● PDF invoice generation

● GST calculation

● Cloud deployment

👨‍💻 <b>Author</b>

<b>Shashank Charpe</b>
MCA Student | Backend Developer (Spring Boot Enthusiast)
