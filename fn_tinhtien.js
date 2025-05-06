// app.js - Hàm tính tổng tiền giỏ hàng

/**
 * Hàm tính tổng tiền giỏ hàng
 * @param {Array} items - Mảng các sản phẩm trong giỏ hàng. Mỗi sản phẩm có `price` và `quantity`.
 * @returns {number} - Tổng số tiền giỏ hàng.
 */
function calculateTotal(items) {
  if (!Array.isArray(items)) {
    throw new Error("Input should be an array.");
  }

  let total = 0;

  // Tính tổng tiền
  items.forEach((item) => {
    if (!item.price || !item.quantity) {
      throw new Error("Each item must have 'price' and 'quantity'.");
    }
    total += item.price * item.quantity;
  });

  return total;
}

module.exports = calculateTotal; // Export hàm để có thể kiểm thử
