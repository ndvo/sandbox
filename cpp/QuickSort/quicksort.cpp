#include <iostream>
#include <chrono>
#include <thread>

void quickSort(int A[], int low, int high);
int partition(int A[], int low, int high);
void swap(int A[], int a, int b);
void printA(int A[], int size);

int main () {
  int A[] = {2, 3, 7, 8, 5, 0, 6, 1, 9, 4};
  std::cout << "Original ";
  printA(A, 10);
  quickSort(A, 0, 9);
  std::cout << "Result ";
  printA(A, 10);
}

void quickSort(int A[], int low, int high) {
  //std::this_thread::sleep_for(std::chrono::milliseconds(500));
  if (low < high) {
    int p = partition(A, low, high);
    std::cout << A << " low: " << low << " high: " << high << " p: " << p << std::endl;
    quickSort(A, low, p-1);
    quickSort(A, p+1, high);
  }
}

int partition(int A[], int low, int high) {
  int originalHigh = high;
  int pivot = A[high];
  high--;
  while (low < high) {
    if (A[low] > pivot) {
      swap(A, low, high);
      high--;
    } else {
      low++;
    }
  }
  swap(A, high, originalHigh);
  return high;
}

void swap(int A[], int a, int b) {
  int temp = A[a];
  A[a] = A[b];
  A[b] = temp;
}

void printA(int A[], int size) {
  for (int i = 0; i < size; i++) {
    std::cout << A[i] << ", " ;
  }
  std::cout << std::endl;
}
