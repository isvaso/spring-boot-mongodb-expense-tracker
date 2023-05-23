package com.isvaso.springbootmongodbexpensetracker.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;

/**
 * Represents an expense.
 * This class encapsulates information about an expense, including its name, category, and amount
 * <p></p>
 * {@code @Document(collection = "expenses")} Specifies the MongoDB collection name for the expense documents.
 */
@Document(collection = "expenses")
public class Expense {

    /**
     * The unique an identifier of expense.
     */
    @Id
    private String id;

    /**
     * The name of the expense.
     */
    @Field(name = "name")
    @Indexed(unique = true)
    private String expenseName;

    /**
     * The category og the expense.
     */
    @Field(name = "category")
    private ExpenseCategory expenseCategory;

    /**
     * The amount of the expense.
     */
    @Field(name = "amount")
    private BigDecimal expenseAmount;

    /**
     * Constructs a new Expense object with the specified properties.
     *
     * @param id              The unique identifier of the expense.
     * @param expenseName     The name of the expense.
     * @param expenseCategory The category of the expense.
     * @param expenseAmount   The amount of the expense.
     */
    public Expense(String id, String expenseName,
                   ExpenseCategory expenseCategory, BigDecimal expenseAmount) {
        this.id = id;
        this.expenseName = expenseName;
        this.expenseCategory = expenseCategory;
        this.expenseAmount = expenseAmount;
    }

    /**
     * Returns unique identifier of the expense.
     *
     * @return The unique identifier of the expense.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the expense.
     *
     * @param id The unique identifier of the expense.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns the name of the expense.
     *
     * @return The name of the expense.
     */
    public String getExpenseName() {
        return expenseName;
    }

    /**
     * Sets the name of the expense.
     *
     * @param expenseName The name of the expense.
     */
    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    /**
     * Returns the category of the expense.
     *
     * @return The category of the expense.
     */
    public ExpenseCategory getExpenseCategory() {
        return expenseCategory;
    }

    /**
     * Sets the category of the expense.
     *
     * @param expenseCategory The category of the expense.
     */
    public void setExpenseCategory(ExpenseCategory expenseCategory) {
        this.expenseCategory = expenseCategory;
    }

    /**
     * Returns the amount of the expense.
     *
     * @return The amount of the expense.
     */
    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    /**
     * Sets the amount of the expense.
     *
     * @param expenseAmount The amount of the expense.
     */
    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    /**
     * Checks if this Expense object is equal to another object.
     *
     * @param o The object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Expense expense = (Expense) o;

        if (id != null ? !id.equals(expense.id) : expense.id != null)
            return false;
        if (!expenseName.equals(expense.expenseName)) return false;
        if (expenseCategory != expense.expenseCategory) return false;
        return expenseAmount.equals(expense.expenseAmount);
    }

    /**
     * Generates a hash code value for this Expense object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + expenseName.hashCode();
        result = 31 * result + expenseCategory.hashCode();
        result = 31 * result + expenseAmount.hashCode();
        return result;
    }

    /**
     * Returns a string representation of the Expense object.
     *
     * @return A string representation of the Expense object.
     */
    @Override
    public String toString() {
        return "Expense{" +
                "id='" + id + '\'' +
                ", expenseName='" + expenseName + '\'' +
                ", expenseCategory=" + expenseCategory +
                ", expenseAmount=" + expenseAmount +
                '}';
    }
}
