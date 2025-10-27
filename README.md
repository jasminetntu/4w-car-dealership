# 🚗 Car Dealership
> **Workshop 4w**
>
> Year Up United (Bay Bytes) - _Technical Academy Fall 2025_
---

## 🏁 Description
A car dealership app interactable through the console. This tool helps users view the current vehicles in stock at a dealership based on specific filters, also allowing them to add or remove vehicles with ease.

Currently, the only dealership that's viewable is "Jammy's Wheels and Deals."

### 💭 Interesting Piece of Code
```java
private int getUniqueID() {
    final int SMALLEST_5_DIGIT_NUM = 10000;
    final int BIGGEST_5_DIGIT_NUM = 99999;

    int vehicleID = 0;
    boolean isUnique = false;

    while (!isUnique) {
        vehicleID = (int) ((Math.random() * (BIGGEST_5_DIGIT_NUM - SMALLEST_5_DIGIT_NUM + 1) + SMALLEST_5_DIGIT_NUM));
        int finalVehicleID = vehicleID;

        isUnique = dealership.getAllVehicles().stream()
            .noneMatch(v -> v.getVehicleId() == finalVehicleID);
    }

    return vehicleID;
}
```

The `getUniqueId()` method returns a 5-digit ID number, ensuring that it hasn't already been assigned to a vehicle.

I found this interesting because it allowed me to experiment with both numeric manipulation and stream usage in a different way. It uses the `Math.random()` method to obtain a decimal between 0 (inclusive) and 1 (exclusive). Then, it performs some operations to transform that decimal into a 5-digit number between 10000 and 99999, both inclusive. 

Finally, it uses a stream to find any matches between the generated ID and the vehicle IDs. If there's a match, then `.noneMatch()` returns false; if there isn't, it returns true, thus terminating the loop and returning a the unique ID.

---

## 📸 Screenshots

### ✦ Home Screen
<img width="434" height="457" alt="image" src="https://github.com/user-attachments/assets/abb37ae7-2c87-42a0-a116-a9af65cb33f4" />

### ✦ Add & Delete a Vehicle
Add (Line 3 in Proof):

<img width="267" height="208" alt="image" src="https://github.com/user-attachments/assets/490693e6-8602-4404-900a-defdc495d950" /> 
<img width="771" height="259" alt="image" src="https://github.com/user-attachments/assets/c3de0f19-2670-4369-bd12-0884661b4619" />

Delete:

<img width="326" height="191" alt="image" src="https://github.com/user-attachments/assets/e423212e-b9ca-436b-88a9-163b23f031c0" />
<img width="773" height="231" alt="image" src="https://github.com/user-attachments/assets/b8ee91ef-1715-437e-9bf4-c194a1e4f6cb" />

### ✦ View All Vehicles
<img width="772" height="620" alt="image" src="https://github.com/user-attachments/assets/57f102be-4576-4a4a-8a2b-e2b919348648" />

### ✦ Erroneous Inputs
<img width="439" height="349" alt="image" src="https://github.com/user-attachments/assets/91919b9f-e51f-4d3d-82b0-c7a102bc6d25" /> <img width="265" height="135" alt="image" src="https://github.com/user-attachments/assets/bcc58ff0-739c-45d3-b290-2b3b91c3dc29" />

