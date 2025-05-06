// test.js - Kiểm thử hàm tính tổng tiền giỏ hàng

const chai = require("chai");
const expect = chai.expect;
const calculateTotal = require("./fn_tinhtien"); // Import hàm tính tổng từ app.js

describe("calculateTotal", () => {
  // Kiểm thử hàm tính tổng khi có các sản phẩm hợp lệ
  it("should calculate the total price correctly", () => {
    const items = [
      { price: 100, quantity: 2 },
      { price: 200, quantity: 1 },
      { price: 50, quantity: 4 },
    ];
    const result = calculateTotal(items);
    expect(result).to.equal(100 * 2 + 200 * 1 + 50 * 4); // 100*2 + 200*1 + 50*4 = 600, code pass
    // expect(result).to.equal(100 + 200 * 1 + 50 * 4); // code fail, dùng để kiểm thử
  });

  // Kiểm thử khi mảng rỗng
  it("should return 0 for an empty cart", () => {
    const items = [];
    const result = calculateTotal(items);
    expect(result).to.equal(0);
  });

  // Kiểm thử khi có sản phẩm không có giá trị hợp lệ (với giá trị thiếu hoặc không hợp lệ)
  it("should throw an error if an item does not have price or quantity", () => {
    const items = [
      { price: 100, quantity: 2 },
      { price: 0, quantity: 1 },
      { price: 50, quantity: -1 }, // Giá trị không hợp lệ
    ];
    expect(() => calculateTotal(items)).to.throw(Error);
  });

  // Kiểm thử với đầu vào không phải là mảng
  it("should throw an error if input is not an array", () => {
    const items = "not an array";
    expect(() => calculateTotal(items)).to.throw("Input should be an array.");
  });
});
