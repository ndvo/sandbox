# Triangle Project Code.

# Triangle analyzes the lengths of the sides of a triangle
# (represented by a, b and c) and returns the type of triangle.
#
# It returns:
#   :equilateral  if all sides are equal
#   :isosceles    if exactly 2 sides are equal
#   :scalene      if no sides are equal
#
# The tests for this method can be found in
#   about_triangle_project.rb
# and
#   about_triangle_project_2.rb
#
def triangle(a, b, c)
  sides = [a, b, c].sort
  if sides[0] <= 0
    raise TriangleError, "no side can have length zero or less"
  end
  if a + b <= c or a + c <= b or b + c <= a 
    raise TriangleError, "the sides do not compose a triangle #{sides}"
  end
  
  if a == b or a == c or b == c
    if a == b and b == c
      return :equilateral
    end
    return :isosceles
  end
  return :scalene
end

# Error class used in part 2.  No need to change this code.
class TriangleError < StandardError
end
