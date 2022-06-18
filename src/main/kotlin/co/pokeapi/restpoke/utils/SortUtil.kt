package co.pokeapi.restpoke.utils

import co.pokeapi.restpoke.domain.dtos.Pokemon
import co.pokeapi.restpoke.domain.enums.Order
import org.springframework.stereotype.Component

@Component
class SortUtil {

    fun sort(list: List<String>, order: Order?): Pokemon {
        val sortedList = mergeSort(list, order)
        return Pokemon(sortedList)
    }

    private fun mergeSort(list: List<String>, order: Order?): List<String> {
        if (list.size <= 1) {
            return list
        }
        val middle = list.size / 2
        val left = list.subList(0, middle)
        val right = list.subList(middle, list.size)
        return merge(mergeSort(left, order), mergeSort(right, order), order)
    }

    private fun merge(left: List<String>, right: List<String>, order: Order?): List<String> {
        val result = ArrayList<String>()
        var leftIndex = 0
        var rightIndex = 0

        while (leftIndex < left.size && rightIndex < right.size) {
            if (isLength(order, left, leftIndex, right, rightIndex)) {
                result.add(left[leftIndex])
                leftIndex++
            } else {
                result.add(right[rightIndex])
                rightIndex++
            }
        }

        while (leftIndex < left.size) {
            result.add(left[leftIndex])
            leftIndex++
        }
        while (rightIndex < right.size) {
            result.add(right[rightIndex])
            rightIndex++
        }
        return result
    }

    private fun isLength(
        order: Order?,
        left: List<String>,
        leftIndex: Int,
        right: List<String>,
        rightIndex: Int
    ): Boolean {
        return if (order == Order.LENGTH) {
            left[leftIndex].length < right[rightIndex].length
        } else
            left[leftIndex] < right[rightIndex]
    }
}